import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD015ProductionEnergiesComponent } from 'app/entities/td-015-production-energies/td-015-production-energies.component';
import { TD015ProductionEnergiesService } from 'app/entities/td-015-production-energies/td-015-production-energies.service';
import { TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

describe('Component Tests', () => {
  describe('TD015ProductionEnergies Management Component', () => {
    let comp: TD015ProductionEnergiesComponent;
    let fixture: ComponentFixture<TD015ProductionEnergiesComponent>;
    let service: TD015ProductionEnergiesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD015ProductionEnergiesComponent],
      })
        .overrideTemplate(TD015ProductionEnergiesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD015ProductionEnergiesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD015ProductionEnergiesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD015ProductionEnergies(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD015ProductionEnergies && comp.tD015ProductionEnergies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
