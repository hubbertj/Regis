(function(conference) {

    if (!conference) {
        return false;
    }

    class RegistrationControlller {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`RegistrationControlller has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new RegistrationControlller();

})(window.conference);