import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ModeVieComponent } from 'app/entities/mode-vie/mode-vie.component';
import { ModeVieService } from 'app/entities/mode-vie/mode-vie.service';
import { ModeVie } from 'app/shared/model/mode-vie.model';

describe('Component Tests', () => {
  describe('ModeVie Management Component', () => {
    let comp: ModeVieComponent;
    let fixture: ComponentFixture<ModeVieComponent>;
    let service: ModeVieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ModeVieComponent],
      })
        .overrideTemplate(ModeVieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeVieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeVieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModeVie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modeVies && comp.modeVies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
