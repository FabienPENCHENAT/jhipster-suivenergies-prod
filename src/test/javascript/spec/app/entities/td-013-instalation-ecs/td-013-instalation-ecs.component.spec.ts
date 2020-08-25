import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD013InstalationECSComponent } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs.component';
import { TD013InstalationECSService } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs.service';
import { TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

describe('Component Tests', () => {
  describe('TD013InstalationECS Management Component', () => {
    let comp: TD013InstalationECSComponent;
    let fixture: ComponentFixture<TD013InstalationECSComponent>;
    let service: TD013InstalationECSService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD013InstalationECSComponent],
      })
        .overrideTemplate(TD013InstalationECSComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD013InstalationECSComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD013InstalationECSService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD013InstalationECS(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD013InstalationECS && comp.tD013InstalationECS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
