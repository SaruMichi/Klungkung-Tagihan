getData();

function getData(){
    this.onmessage = function(event){
        whileData(event);
    };
}

function whileData(event){
    var statusPay = true;
    var incr = 0; 
    
    sleep(10000);
    var ress = run(event.data[4]+'/AjaxPaymentChanel?command=6&noid='+event.data[0]+'&bulan='+event.data[1]+'&tahun='+event.data[2]+'&tagihan='+event.data[3]+'');
        
}
                
async  function sleep(milliseconds) {
    const date = Date.now();
    let currentDate = null;
    do {
      currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}

function run(url) {
  
    // Creating Our XMLHttpRequest object 
    var xhr = new XMLHttpRequest();

    // Making our connection  
    var url = url;
    xhr.open("POST", url, true);

    // function execute after request is successful 
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log();
            postMessage(this.responseText);
        }
    }
    // Sending our request 
    xhr.send();
}