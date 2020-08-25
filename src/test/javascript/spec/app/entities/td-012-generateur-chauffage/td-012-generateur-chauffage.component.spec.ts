import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD012GenerateurChauffageComponent } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.component';
import { TD012GenerateurChauffageService } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.service';
import { TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

describe('Component Tests', () => {
  describe('TD012GenerateurChauffage Management Component', () => {
    let comp: TD012GenerateurChauffageComponent;
    let fixture: ComponentFixture<TD012GenerateurChauffageComponent>;
    let service: TD012GenerateurChauffageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD012GenerateurChauffageComponent],
      })
        .overrideTemplate(TD012GenerateurChauffageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD012GenerateurChauffageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD012GenerateurChauffageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD012GenerateurChauffage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD012GenerateurChauffages && comp.tD012GenerateurChauffages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
