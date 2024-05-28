import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-date-time-picker',
  templateUrl: './date-time-picker.component.html',
  styleUrls: ['./date-time-picker.component.css']
})
export class DateTimePickerComponent implements OnInit {
  @Output() dateTimeChange = new EventEmitter<Date>();
  dateTimeForm: FormGroup;
  meridian = true;
  hourStep = 1;
	minuteStep = 15;

  constructor(private fb: FormBuilder) {
    this.dateTimeForm = this.fb.group({
      date: [null],
      time: { hour: 13, minute: 30, second: 0 }
    });
  }

  ngOnInit(): void {
    this.dateTimeForm.valueChanges.subscribe(value => {
      const { date, time } = value;
      if (date && time) {
        const dateTime = new Date(
          date.year, date.month - 1, date.day,
          time.hour, time.minute, time.second
        );
        this.dateTimeChange.emit(dateTime);
      }
    });
  }
}
