import React from "react";
import { Doughnut } from "react-chartjs-2";
import { Chart, registerables, ArcElement } from "chart.js";
Chart.register(...registerables);
Chart.register(ArcElement);

const data = {
  labels: ["Protien", "Carbs", "Fat"],
  datasets: [
    {
      data: [99, 166, 24],
      backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56"],
      hoverBackgroundColor: ["#FF6384", "#36A2EB", "#FFCE56"],
      borderWidth: 1
    }
  ]
};
export default function Data() {
  const onClick = (e) => {
    console.log(e);
  };
  return <Doughnut data={data} onClick={(e) => onClick(e)} />;
}
