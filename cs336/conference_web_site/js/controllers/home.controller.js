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
            console.log(`HomeController has been init with ${JSON.stringify(data)}`);
        }

    }

    conference.controller = new HomeController();

})(window.conference);