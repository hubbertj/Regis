(function(conference) {

    if (!conference) {
        return false;
    }

    class ActivatesController {
        constructor() {}
        
        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`ActivatesController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new ActivatesController(10, 10);

})(window.conference);