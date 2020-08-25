import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD008BaieComponent } from 'app/entities/td-008-baie/td-008-baie.component';
import { TD008BaieService } from 'app/entities/td-008-baie/td-008-baie.service';
import { TD008Baie } from 'app/shared/model/td-008-baie.model';

describe('Component Tests', () => {
  describe('TD008Baie Management Component', () => {
    let comp: TD008BaieComponent;
    let fixture: ComponentFixture<TD008BaieComponent>;
    let service: TD008BaieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD008BaieComponent],
      })
        .overrideTemplate(TD008BaieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD008BaieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD008BaieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD008Baie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD008Baies && comp.tD008Baies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
