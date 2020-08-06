(function(conference) {

    if (!conference) {
        return false;
    }

    class AwardsController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`AwardsController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new AwardsController();

})(window.conference);