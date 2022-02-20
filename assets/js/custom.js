//Form Steps
const steps = Array.from(document.querySelectorAll("form .step"));  
 const nextBtn = document.querySelectorAll("form .next-btn");  
 const prevBtn = document.querySelectorAll("form .previous-btn");  
 const form = document.querySelector("form");  
 nextBtn.forEach((button) => {  
  button.addEventListener("click", () => {  
   changeStep("next");  
  });  
 });  
 prevBtn.forEach((button) => {  
  button.addEventListener("click", () => {  
   changeStep("prev");  
  });  
 });  
 form.addEventListener("submit", (e) => {  
  e.preventDefault();  
  const inputs = [];  
  form.querySelectorAll("input").forEach((input) => {  
   const { name, value } = input;  
   inputs.push({ name, value });  
  });  
  console.log(inputs);  
  form.reset();  
 });  
 function changeStep(btn) {  
  let index = 0;  
  const active = document.querySelector(".active");  
  index = steps.indexOf(active);  
  steps[index].classList.remove("active");  
  if (btn === "next") {  
   index++;  
  } else if (btn === "prev") {  
   index--;  
  }  
  steps[index].classList.add("active");  
 } 


 //OTP Inputs
 function OTPInput() {
    const inputs = document.querySelectorAll('#otp > *[id]');
    for (let i = 0; i < inputs.length; i++) {
      inputs[i].addEventListener('keydown', function(event) {
        if (event.key === "Backspace") {
          inputs[i].value = '';
          if (i !== 0)
            inputs[i - 1].focus();
        } else {
          if (i === inputs.length - 1 && inputs[i].value !== '') {
            return true;
          } else if (event.keyCode > 47 && event.keyCode < 58) {
            inputs[i].value = event.key;
            if (i !== inputs.length - 1)
              inputs[i + 1].focus();
            event.preventDefault();
          } else if (event.keyCode > 64 && event.keyCode < 91) {
            inputs[i].value = String.fromCharCode(event.keyCode);
            if (i !== inputs.length - 1)
              inputs[i + 1].focus();
            event.preventDefault();
          }
        }
      });
    }
  }
  OTPInput();


