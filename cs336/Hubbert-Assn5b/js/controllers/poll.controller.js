(function(conference) {

    if (!conference) {
        return false;
    }

    class PollController {
        constructor() {
            $('#poll-form').on("submit", jQuery.proxy(this, "onSubmit"));
        }

        /**
         * init the controller with any data from frontend
         * @return {[type]}
         */
        init(data) {
            console.log(`PollController has been init with ${JSON.stringify(data)}`);
        }

        /**
         * Handles the submittion
         * @return {[type]} [description]
         */
        onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });
            const { nomineeRadio } = data;
            const vote = nomineeRadio.replace('_', ' ').split(' ')
                .map((s) => s.charAt(0).toUpperCase() + s.substring(1))
                .join(' ');
            if (vote) {
                alert(`Thank you for voting for: ${vote}`);
            }
            return false;
        }


    }

    conference.controller = new PollController();

})(window.conference);