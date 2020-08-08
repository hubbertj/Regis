(function(conference) {

    if (!conference) {
        return false;
    }

    class ListsController {
        constructor() {

        }

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            this.tableOptions = {
                data: [],
                columns: [
                    { data: 'id' },
                    {
                        "mData": "date",
                        "mRender": function(pass, type, data, row) {
                            return new Date(data.date).toLocaleString();
                        }
                    },
                    {
                        "mData": "name",
                        "mRender": function(pass, type, data, row) {
                            return `${data.title.charAt(0).toUpperCase() + data.title.slice(1)} 
                            ${data.firstname.charAt(0).toUpperCase() + data.firstname.slice(1)} 
                            ${data.lastname.charAt(0).toUpperCase() + data.lastname.slice(1)}`;
                        }
                    },
                    {
                        "mData": "job title",
                        "mRender": function(pass, type, data, row) {
                            return data.job_title;
                        }
                    },
                    { data: 'company' },
                    {
                        "mData": "actions",
                        "mRender": function(pass, type, data, row) {
                            return `<a href="/thankyou?confirmation=${data.confirmation}" class="btn btn-sm btn-primary" target="_blank">details</a>`
                        },
                        "orderable": false
                    },
                ]
            };
            this.datatable = $('#search-resuls-table').dataTable(this.tableOptions);
            this.dt = new $.fn.dataTable.Api('#search-resuls-table');
            $('input[type="radio"], input[type="checkbox"]').checkboxradio();

            $('.workshop-radio-container input[type="radio"], .all-radio-container input[type="radio"]').click(function() {
                if (this.previous) {
                    this.checked = false;
                }
                this.previous = this.checked;
                $(this).checkboxradio('refresh');
            });

            $('#search-form input[type="radio"]:not([name="all_registrants"])').on('click', (e) => {
                $('input[type="radio"][name="all_registrants"]').prop('checked', false).checkboxradio('refresh');
            });

            $('#all_registrants').on('click', jQuery.proxy(this, "onAllClick"));

        }

        /**
         * Clears search form when all radio is checked
         * @param  {obj} e
         * @return {boolean} false to stop the event
         */
        onAllClick(e) {
            const isOn = $(e.currentTarget).prop('checked');
            if (isOn) {
                this.clearFormButAll();
            }
            return false;
        }

        /**
         * Clears search form but the all radio
         * @return {null}
         */
        clearFormButAll() {
            const formElem = $('#search-form');
            formElem.find('input:not([type=checkbox]):not([type=radio]), textarea, select').val('');
            formElem.find('input[type=radio]:not([name="all_registrants"])')
                .prop('checked', false).checkboxradio('refresh');

        }

        /**
         * Handles the user search field
         * @param  {obj} e event
         * @return {boolean} stops event
         */
        async onSubmit(e) {
            let data = {};
            let f_data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });

            if (!$.isEmptyObject(data)) {
                if ('meal_pack' in data) {
                    f_data.meal_pack = data.meal_pack;
                    delete data.meal_pack;
                }
                if (!$.isEmptyObject(data)) {
                    f_data.workshops = Object.values(data);
                }
            }
 
            try {
                const registrantList = await this.getRegisteredUserList(f_data);
                this.dt.clear();
                this.dt.rows.add(registrantList);
                this.dt.draw();

            } catch (err) {
                const errorMessage = JSON.parse(err.responseText).message || err;
                conference.alert(errorMessage, 'danger');
            }

            e.preventDefault();
            e.stopPropagation();
            return false;

        }

        /**
         * Calls backend for list of Registered User from search criteria
         * @param  {obj} searchCriteria 
         * @return {Promise}
         */
        getRegisteredUserList(searchCriteria) {
            return new Promise((results, reject) => {
                $.ajax({
                    url: `/registrant/search`,
                    type: "GET",
                    data: searchCriteria,
                    success: (response) => {
                        if ('registrants' in response) {
                            results(response.registrants);
                        } else {
                            results(response);
                        }
                    },
                    error: (err) => {
                        reject(err);
                    },
                });
            });
        }
    }

    conference.controller = new ListsController();

})(window.conference);