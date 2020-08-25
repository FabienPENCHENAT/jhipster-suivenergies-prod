import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD014GenerateurECSComponent } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.component';
import { TD014GenerateurECSService } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.service';
import { TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

describe('Component Tests', () => {
  describe('TD014GenerateurECS Management Component', () => {
    let comp: TD014GenerateurECSComponent;
    let fixture: ComponentFixture<TD014GenerateurECSComponent>;
    let service: TD014GenerateurECSService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD014GenerateurECSComponent],
      })
        .overrideTemplate(TD014GenerateurECSComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD014GenerateurECSComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD014GenerateurECSService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD014GenerateurECS(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD014GenerateurECS && comp.tD014GenerateurECS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
