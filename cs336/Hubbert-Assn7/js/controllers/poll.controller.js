(function(conference) {

    if (!conference) {
        return false;
    }

    class PollController {
        constructor() {
            $('#poll-form').on("submit", jQuery.proxy(this, "onSubmit"));
            $('input[type="radio"]').click(function() {
                if (this.previous) {
                    this.checked = false;
                }
                this.previous = this.checked;
                $(this).checkboxradio('refresh');
            });

            $('input[name="nomineeRadio"]').checkboxradio();
            this.updateVotes();
        }

        /**
         * Init the controller with any data from frontend
         * @return {null}
         */
        init(data) {
            console.log(`PollController has been init with ${JSON.stringify(data)}`);
        }

        /**
         * Updates the view with current vote numbers
         * @return {[type]}
         */
        updateVotes() {
            $('.votes').each(function() {
                const nominee = $(this).parent().parent().find('input[type="radio"]').val();
                if (nominee && conference.getLocalStorage(nominee)) {
                    $(this).text(conference.getLocalStorage(nominee));
                } else {
                    $(this).text(0);
                }
            });
        }

        /**
         * Resets form
         * @return {boolean} [description]
         */
        reset() {
            $('input[type="radio"]').prop('checked', false).checkboxradio('refresh');
            return false;
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
                const voteCount = conference.getLocalStorage(nomineeRadio) + 1;
                conference.setLocalStorage(nomineeRadio, voteCount);
                this.updateVotes();
                this.reset();
                alert(`Thank you for voting for: ${vote}`);
            }
            return false;
        }
    }

    conference.controller = new PollController();

})(window.conference);