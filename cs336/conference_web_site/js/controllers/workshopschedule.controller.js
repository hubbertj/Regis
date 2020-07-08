(function(conference) {

    if (!conference) {
        return false;
    }

    class WorkshopscheduleController {
        constructor() {}
        
        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`WorkshopscheduleController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new WorkshopscheduleController();

})(window.conference);