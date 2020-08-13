import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { InfoDPEComponent } from 'app/entities/info-dpe/info-dpe.component';
import { InfoDPEService } from 'app/entities/info-dpe/info-dpe.service';
import { InfoDPE } from 'app/shared/model/info-dpe.model';

describe('Component Tests', () => {
  describe('InfoDPE Management Component', () => {
    let comp: InfoDPEComponent;
    let fixture: ComponentFixture<InfoDPEComponent>;
    let service: InfoDPEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [InfoDPEComponent],
      })
        .overrideTemplate(InfoDPEComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfoDPEComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfoDPEService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InfoDPE(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.infoDPES && comp.infoDPES[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
