(function(conference) {

    if (!conference) {
        return false;
    }

    class AdminController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`AdminController has been init with ${JSON.stringify(data)}`);
        }

    }

    conference.controller = new AdminController();

})(window.conference);