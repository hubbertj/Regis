(function(conference) {

    if (!conference) {
        return false;
    }

    class ActiviteController {
        constructor() {}
        
        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {

        }


    }

    conference.controller = new ActiviteController(10, 10);

})(window.conference);