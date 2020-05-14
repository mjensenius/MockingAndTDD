var url = "http://localhost:8080";

function getBank() {
    let number = document.getElementById("input_bank_id").value;
    fetch(url +"/banking/api/bank/" + number)
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
    let number = document.getElementById("input_customer_number").value;
    fetch(url + "/banking/api/customer/" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("customer").innerText = data;
                document.getElementById("err_customer").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("customer").innerText = "";
                document.getElementById("err_customer").innerText = err;
            });
}

function getAccounts() {
    let number = document.getElementById("input_customer_account_number").value;
    fetch(url + "/banking/api/customer/accounts?id=" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("customer_accounts").innerText = data;
                document.getElementById("err_customer_accounts").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("customer_accounts").innerText = "";
                document.getElementById("err_customer_accounts").innerText = err;
            });
}



function getBankAccounts() {
    let number = document.getElementById("input_bank_accounts_id").value;
    fetch(url +"/banking/api/bank/accounts/" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("bank_acc_id").innerText = data;
                document.getElementById("err_bank_acc_id").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("bank_acc_id").innerText = "";
                document.getElementById("err_bank_acc_id").innerText = err;
            });
}

function getAccount() {
    let number = document.getElementById("input_account_id").value;
    fetch(url + "/banking/api/account/" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("account").innerText = data;
                document.getElementById("err_account").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("account").innerText = ""
                document.getElementById("err_account").innerText = err;
            });
}

function getBalance() {
    let number = document.getElementById("input_acc_balance_id").value;
    fetch(url + "/banking/api/account/balance?id=" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("balance").innerText = data;
                document.getElementById("err_balance").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("balance").innerText = ""
                document.getElementById("err_balance").innerText = err;
            });
}

function getWithdrawals() {
    let number = document.getElementById("input_acc_withdrawals").value;
    fetch(url + "/banking/api/account/withdrawals?id=" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("withdrawals").innerText = data;
                document.getElementById("err_withdrawals").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("withdrawals").innerText = ""
                document.getElementById("err_withdrawals").innerText = err;
            });
}

function getDeposits() {
    let number = document.getElementById("input_acc_withdrawals").value;
    fetch(url + "/banking/api/account/deposits?id=" + number)
            .then(res => {
                if (!res.ok)
                    throw new Error(res.status + " (" + res.statusText + ").");
                return res.text();
            })
            .then(data => {
                document.getElementById("deposits").innerText = data;
                document.getElementById("err_deposits").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("deposits").innerText = ""
                document.getElementById("err_deposits").innerText = err;
            });
}

function transferByAccId() {
    let amount = document.getElementById("transfer_acc_id_amount").value;
    let source = document.getElementById("transfer_acc_id_source").value;
    let target = document.getElementById("transfer_acc_id_target").value;

    let params = {amount: amount, source: source, target: target};
    let urlParams = new URLSearchParams(Object.entries(params));

    fetch(url + '/banking/api/account/transfer/id?' + urlParams, {
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
                document.getElementById("transfer_acc_id_statuscode").innerText = data;
                document.getElementById("err_transfer_acc_id_statuscode").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("transfer_acc_id_statuscode").innerText = "";
                document.getElementById("err_transfer_acc_id_statuscode").innerText = err;
            });
}

function transferByAccNumber() {
    let amount = document.getElementById("transfer_acc_number_amount").value;
    let source = document.getElementById("transfer_acc_number_source").value;
    let target = document.getElementById("transfer_acc_number_target").value;

    let params = {amount: amount, source: source, target: target};
    let urlParams = new URLSearchParams(Object.entries(params));

    fetch(url + '/banking/api/account/transfer/number?' + urlParams, {
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
                document.getElementById("transfer_acc_number_statuscode").innerText = data;
                document.getElementById("err_transfer_acc_number_statuscode").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("transfer_acc_number_statuscode").innerText = "";
                document.getElementById("err_transfer_acc_number_statuscode").innerText = err;
            });
}

function deposit() {
    let amount = document.getElementById("deposit_amount").value;
    let id = document.getElementById("deposit_id").value;
 
    let params = {amount: amount,id: id};
    let urlParams = new URLSearchParams(Object.entries(params));

    fetch(url + '/banking/api/account/deposit?' + urlParams, {
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
                document.getElementById("deposit").innerText = data;
                document.getElementById("err_deposit").innerText = ""; //clear previous error 
            })
            .catch(err => {
                document.getElementById("deposit").innerText = "";
                document.getElementById("err_deposit").innerText = err;
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

