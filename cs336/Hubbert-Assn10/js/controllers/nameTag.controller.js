(function(conference) {

    if (!conference) {
        return false;
    }

    class NameTagController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`NameTagController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new NameTagController();

})(window.conference);