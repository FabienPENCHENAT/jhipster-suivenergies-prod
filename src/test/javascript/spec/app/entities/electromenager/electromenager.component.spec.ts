import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ElectromenagerComponent } from 'app/entities/electromenager/electromenager.component';
import { ElectromenagerService } from 'app/entities/electromenager/electromenager.service';
import { Electromenager } from 'app/shared/model/electromenager.model';

describe('Component Tests', () => {
  describe('Electromenager Management Component', () => {
    let comp: ElectromenagerComponent;
    let fixture: ComponentFixture<ElectromenagerComponent>;
    let service: ElectromenagerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ElectromenagerComponent],
      })
        .overrideTemplate(ElectromenagerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElectromenagerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElectromenagerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Electromenager(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.electromenagers && comp.electromenagers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
