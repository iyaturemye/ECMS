/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ctx = document.getElementById('lineChart').getContext('2d');
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'line',
                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June", "July","August","September","October","November","December"],
                    datasets: [{
                            label: "2018 Accident",
                            backgroundColor: 'rgb(0,150,255)',
                            borderColor: 'rgb(0,120,255)',
                            data: [0, 10, 5, 2, 20, 30, 45,25,32,2,15,22],
                        }]
                },
                // Configuration options go here
                options: {}
            });

            var ctx = document.getElementById('barChart').getContext('2d');
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'bar',
                // The data for our dataset
                data: {
                    labels: ["January", "February", "March", "April", "May", "June", "July","August","September","October","November","December"],
                    datasets: [{
                            label: "Paid amount",
                            backgroundColor: 'rgb(255, 99, 132)',
                            borderColor: 'rgb(255, 99, 132)',
                            data: [100000, 150000, 500000,2000000, 4000000, 8000000, 1000000,300000,110000,20000000,10000000,100000],
                        }]
                },
                // Configuration options go here
                options: {}
            });