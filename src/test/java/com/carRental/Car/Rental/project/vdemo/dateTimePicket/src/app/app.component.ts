// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup } from '@angular/forms';
// import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent {
//   dateTimeForm: FormGroup;

//   constructor(private fb: FormBuilder) {
//     this.dateTimeForm = this.fb.group({
//       date: [null],
//       time: { hour: 13, minute: 30, second: 0 }
//     });
//   }

//   submit() {
//     const formValue = this.dateTimeForm.value;
//     console.log('Date:', formValue.date);
//     console.log('Time:', formValue.time);
//   }
// }


import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      datetime: [null]
    });
  }

  updateDateTime(dateTime: Date) {
    this.form.patchValue({ datetime: dateTime });
  }

  submit() {
    const formValue = this.form.value;
    console.log('Date and Time:', formValue.datetime);
  }
}

