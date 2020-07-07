(function(conference) {

    if (!conference) {
        return false;
    }

    class HomeController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {

        }

    }

    conference.controller = new HomeController();

})(window.conference);