/**
 * 
 */
(function() {
    var currentDate = new Date();
    var element = document.querySelector('#test1');
    var months = ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"];
    var month=currentDate.getMonth();
    
    element.innerHTML = "<span class=\"badge\">Poprzedni miesiąc</span><> "+ months[month] + " "  + currentDate.getFullYear()+" <span class=\"badge\">Następny miesiąc</span>";
    var element = document.querySelector('#test2');
    
    element.innerHTML = "<tr>";
    currentDate.setMonth(currentDate.getMonth()+1);
    currentDate.setDate(1);
    var month1=currentDate.getMonth();
    currentDate.setDate(currentDate.getDate()-1);
    var text = currentDate.getFullYear() + "-" + months[currentDate.getMonth()] + "-" + currentDate.getDate();
    var number=currentDate.getDate();
    document.querySelector('#test2').innerHTML = text;
    
    var table = document.getElementById('tabelka'); //pobieramy tabelkę

    var tableHTML = ''; //rozpoczynamy generowanie kodu tabeli
    for (var y=1; y<=5; y++) {

        var tr = '<tr>'; //zaczynam tworzyć nowe tr

        for (var x=1; x<=7; x++) {
            var tekst = x*y; //tworzymy tekst do wstawienia do komórki

            if (y==1 || x==1) { //jeżeli jest to pierwsza komórka w pionie lub poziomie
                var td = '<th>'+tekst+'</th>'; //stwórz nowe th
            } else {
                var td = '<td>'+tekst+'</td>'; //stwórz nowe td
            }

            tr += td; //wstaw komórkę do rzędu TR
        }
        tr += '</tr>'; //zakończ tworzenie rzędu

        tableHTML += tr; //wstaw rząd do generowanego kodu HTML
    }

    table.innerHTML = tableHTML; //gotowy wygenerowany kod HTML wstawiamy do tabeli na stronie

})();