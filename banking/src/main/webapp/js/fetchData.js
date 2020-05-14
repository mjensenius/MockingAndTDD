var url = "http://localhost:8080";

function getBank() {
    let id = document.getElementById("bank_id").value;
    fetch(url +"/banking/api/bank/" + id)
        .then(res => {
            if (!res.ok)
               throw new Error(res.status + " (" + res.statusText + ").");
            return res.text();
        })
        .then(data => {
            document.getElementById("bank_res").innerText = data;
        })
        .catch(err => {
            document.getElementById("bank_res").innerText = err;
        });
}

function getCustomer() {
    let id = document.getElementById("customer_id").value;
    fetch(url + "/banking/api/customer/" + id)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("customer_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("customer_res").innerText = err;
            });
}

function getAccount() {
    let id = document.getElementById("account_id").value;
    fetch(url + "/banking/api/account/" + id)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("account_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("account_res").innerText = err;
            });
}

function getBalance() {
    let id = document.getElementById("balance_account_id").value;
    fetch(url + "/banking/api/account/" + id + "/balance")
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("balance_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("balance_res").innerText = err;
            });
}

function getWithdrawals() {
    let id = document.getElementById("withdrawal_account_id").value;
    fetch(url + "/banking/api/account/" + id + "/withdrawals")
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("withdrawal_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("withdrawal_res").innerText = err;
            });
}

function getDeposits() {
    let id = document.getElementById("deposit_account_id").value;
    fetch(url + "/banking/api/account/" + id + "/deposits")
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("deposit_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("deposit_res").innerText = err;

            });
}

function transferByAccountId() {
    let amount = document.getElementById("transfer_acc_id_amount").value;
    let id = document.getElementById("transfer_acc_id_source").value;
    let target = document.getElementById("transfer_acc_id_target").value;


    fetch(url + "/banking/api/account/" + id + "/transfer?amount="+amount+"&target="+target, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })

            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("transferId_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("transferId_res").innerText = err;
            });
}

function transferByAccountNumber() {
    let amount = document.getElementById("transfer_acc_number_amount").value;
    let source = document.getElementById("transfer_acc_number_source").value;
    let target = document.getElementById("transfer_acc_number_target").value;

    let params = {amount: amount, source: source, target: target};
    let urlParams = new URLSearchParams(Object.entries(params));

    fetch(url + '/banking/api/account/transfer/number?amount='+amount+"&source="+source+"&target="+target , {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })

            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("transferNo_res").innerText = data;
            })
            .catch(err => {
                document.getElementById("transferNo_res").innerText = err;
            });
}

function putDeposit() {
    
    let id = document.getElementById("deposit_account_id_put").value;
    let amount = document.getElementById("deposit_account_amount_put").value;
 

    fetch(url + "/banking/api/account/"+id+"/deposit?amount="+amount , {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })

            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("deposit_res_put").innerText = data;
            })
            .catch(err => {
                document.getElementById("deposit_res_put").innerText = "";
            });
}

function withdraw() {
    let amount = document.getElementById("withdraw_amount").value;
    let id = document.getElementById("withdraw_id").value;
 
    let params = {amount: amount,id: id};
    let urlParams = new URLSearchParams(Object.entries(params));

    fetch(url + '/banking/api/account/withdraw?' + urlParams, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })

            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("withdraw").innerText = data;
                document.getElementById("err_withdraw").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("withdraw").innerText = "";
                document.getElementById("err_withdraw").innerText = err;
            });
}

