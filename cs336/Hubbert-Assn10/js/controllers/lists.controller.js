(function(conference) {

    if (!conference) {
        return false;
    }

    class ListsController {
        constructor() {
            this.tableOptions = {};
            this.table = $('#search-resuls-table').dataTable(this.tableOptions);
        }

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {}

        /**
         * Handles the user search field
         * @param  {obj} e event
         * @return {boolean} stops event
         */
        async onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });

            try {
                const registrantList = await this.getRegisteredUserList(data);
                console.log(registrantList)
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