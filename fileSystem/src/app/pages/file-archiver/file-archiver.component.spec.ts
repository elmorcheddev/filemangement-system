import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileArchiverComponent } from './file-archiver.component';

describe('FileArchiverComponent', () => {
  let component: FileArchiverComponent;
  let fixture: ComponentFixture<FileArchiverComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FileArchiverComponent]
    });
    fixture = TestBed.createComponent(FileArchiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
