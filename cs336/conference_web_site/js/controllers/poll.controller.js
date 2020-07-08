(function(conference) {

    if (!conferance) {
        return false;
    }

    class PollController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`PollController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new PollController();

})(window.conference);