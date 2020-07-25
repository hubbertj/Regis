(function(conference) {

    if (!conference) {
        return false;
    }

    class ActivitiesController {
        constructor() {}
        
        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`ActivitiesController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new ActivitiesController(10, 10);

})(window.conference);