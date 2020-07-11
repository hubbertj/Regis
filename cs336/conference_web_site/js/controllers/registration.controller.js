(function(conference) {

    if (!conference) {
        return false;
    }

    class RegistrationController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`RegistrationController has been init with ${JSON.stringify(data)}`);
        }

        /**
         * Handles the submittion
         * @return {[type]} [description]
         */
        onSubmit(){
            window.location.href = 'thankyou.html';
            return false;
        }


    }

    conference.controller = new RegistrationController();

})(window.conference);