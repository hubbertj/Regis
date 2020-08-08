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
                    let registration = await this.getRegistration(confirmation);

                    registration.session1 = registration.session1 || 'none';
                    registration.session2 = registration.session2 || 'none';
                    registration.session3 = registration.session3 || 'none';

                    registration.session1 = registration.session1.replace(/_/g, ' ');
                    registration.session2 = registration.session2.replace(/_/g, ' ');
                    registration.session3 = registration.session3.replace(/_/g, ' ');

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