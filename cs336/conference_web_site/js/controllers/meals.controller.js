(function(conference) {

    if (!conference) {
        return false;
    }

    class MealsController {
        constructor() {}

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`MealsController has been init with ${JSON.stringify(data)}`);
        }


    }

    conference.controller = new MealsController();

})(window.conference);