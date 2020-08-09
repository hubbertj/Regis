(function(conference) {

    if (!conference) {
        return false;
    }

    class PollController {
        constructor() {}

        /**
         * Init the controller with any data from frontend
         * @return {null}
         */
        init(data) {
            this.updateVotes();
        }

        /**
         * Updates the view with current vote numbers
         * @return {[type]}
         */
        async updateVotes(p_nominees) {
            $('.nominees-container').empty();
            let nominees;
            if (p_nominees) {
                nominees = p_nominees;
            } else {
                try {
                    nominees = await this.getNominees();
                } catch (err) {
                    conference.alert(err, 'danger');
                }
            }
            if (nominees && nominees.length > 0) {
                nominees.forEach(nominee => $("#nominee-template").tmpl(nominee).appendTo(".nominees-container"));
            }
            $('input[type="radio"]').click(function() {
                if (this.previous) {
                    this.checked = false;
                }
                this.previous = this.checked;
                $(this).checkboxradio('refresh');
            });
            $('input[name="nominee_id"]').checkboxradio();
        }

        /**
         * Gets all Nominees from the system
         * @return {Promise}
         */
        getNominees() {
            return new Promise((results, reject) => {
                $.ajax({
                    url: `/nominee`,
                    type: "GET",
                    data: null,
                    success: (response) => {
                        if ('nominees' in response) {
                            results(response.nominees);
                        } else {
                            results(response);
                        }
                    },
                    error: (err) => {
                        reject(err);
                    },
                });
            });
        }

        /**
         * updates votes on the server
         * @param  {int} nominee_id 
         * @param  {obj} data
         * @return {Promise} 
         */
        onUpdateVotes(nominee_id, data) {
            return new Promise((results, reject) => {
                $.ajax({
                    url: `/nominee/${nominee_id}`,
                    type: "PUT",
                    data: data,
                    success: (response) => {
                        if ('nominees' in response) {
                            results(response.nominees);
                        } else {
                            results(response);
                        }
                    },
                    error: (err) => {
                        reject(err);
                    },
                });
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
        async onSubmit(e) {
            let data = {};
            $($(e.currentTarget).serializeArray()).each(function(index, obj) {
                data[obj.name] = obj.value;
            });
            const { nominee_id } = data;
            let nomineesList;
            try {
                if (nominee_id) {
                    nomineesList = await this.onUpdateVotes(nominee_id, { votes: 1 });
                    const nominee = nomineesList.find(nominee => nominee.id == parseInt(nominee_id));
                    conference.alert(`Vote casted for ${nominee.name}`, 'success');
                    this.updateVotes(nomineesList);
                }
            } catch (err) {
                if ('statusText' in err) {
                    conference.alert(`Failed to cast vote [error=${err.statusText.toLowerCase()}]`, 'danger');
                } else {
                    conference.alert(`Failed to cast vote [error=${err}]`, 'danger');
                }
                console.error(err);
            }
            this.updateVotes();
            return false;
        }
    }

    conference.controller = new PollController();

})(window.conference);