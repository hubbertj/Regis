(function(conference) {

    if (!conference) {
        return false;
    }

    class ThankyouController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        async init(data) {
            const confirmation = $.urlParam('confirmation');
            if (confirmation) {
                try {
                    const registration = await this.getRegistration(confirmation);
                    $("#registrationInfoTemplate").tmpl(registration).appendTo("#registration-container");
                } catch (err) {
                    const errorMessage = err.responseJSON.message || err;
                    console.error(err);
                    conference.alert(errorMessage, 'danger');
                }
            }
        }

        /**
         * calls backend and get Registration obj by ID
         * @param  {int} id
         * @return {Promise}
         */
        getRegistration(confirmation) {
            return new Promise((result, reject) => {
                $.ajax({
                    url: `/registrant/${confirmation}`,
                    type: "GET",
                    data: null,
                    success: (response) => {
                        if('registrant' in response){
                            result(response.registrant);
                        }else{
                            result(response);
                        }
                    },
                    error: (err) => {
                        reject(err)
                    },
                });
            });
        }


    }

    conference.controller = new ThankyouController();

})(window.conference);