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
            this.tableOptions = {};
            this.table = $('#search-resuls-table').dataTable(this.tableOptions);
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
            console.log(f_data);
            try {
                const registrantList = await this.getRegisteredUserList(f_data);
                console.log(registrantList);
            } catch (err) {
                console.error(err);
                conference.alert(err, 'danger');
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