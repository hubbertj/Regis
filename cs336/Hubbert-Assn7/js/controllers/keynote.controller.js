(function(conference) {

    if (!conference) {
        return false;
    }

    class KeynoteController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`KeynoteController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new KeynoteController();

})(window.conference);