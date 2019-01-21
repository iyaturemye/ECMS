
function PrintElem(file,hideDive)
    {
      //  alert('jjj');
         document.getElementById(hideDive).style.display = "block";
              // document.getElementById(hprint).style.display = "block";
        PopUpFile($(file).html());
    }

    function PopUpFile(data)
    {
        
     // alert(data); 
        var printCart = window.open('', 'Product Items');
        printCart.document.write('<html><head><title>Report</title>');
        
        printCart.document.write('<style> table{ border: solid 1px black; width:670px; border-collapse: collapse; }   </style></head><body >');
      
               printCart.document.write('<center>'); 
              // printCart.document.write(' <img src="layout/image/Bannerogg.png" alt="..." width="600px" class="img-rounded/>" ');
        //printCart.document.write('<h2 >Journey  of  Requisitions Report from <%=fromDate%> to <%=toDate%>  </h2>');
        printCart.document.write(data);
               printCart.document.write('</center>');
        printCart.document.write('</br>');
      
        printCart.document.write('</body></html>');

        printCart.print();
        printCart.close();

        return true;
    }