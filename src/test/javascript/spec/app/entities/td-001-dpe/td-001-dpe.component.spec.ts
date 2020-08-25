import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD001DPEComponent } from 'app/entities/td-001-dpe/td-001-dpe.component';
import { TD001DPEService } from 'app/entities/td-001-dpe/td-001-dpe.service';
import { TD001DPE } from 'app/shared/model/td-001-dpe.model';

describe('Component Tests', () => {
  describe('TD001DPE Management Component', () => {
    let comp: TD001DPEComponent;
    let fixture: ComponentFixture<TD001DPEComponent>;
    let service: TD001DPEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD001DPEComponent],
      })
        .overrideTemplate(TD001DPEComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD001DPEComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD001DPEService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD001DPE(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD001DPES && comp.tD001DPES[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
