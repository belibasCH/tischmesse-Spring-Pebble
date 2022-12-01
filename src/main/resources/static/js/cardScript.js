const changeCard = (id) =>{
    const nodeContent = document.getElementById("card-"+id);
    const nodeButton = document.getElementById("button-"+id);
   const currentStyle = nodeContent.style.display;
   console.log(currentStyle);
   if (currentStyle == "none"){
       nodeContent.style.display ="flex";
       nodeButton.src= "/img/minus-icon.svg";
       console.log(nodeButton.src);
   }
   else{
       nodeContent.style.display ="none";
       nodeButton.src= "/img/plusRound-icon.svg";
       console.log(nodeButton.src);

   }

} ;