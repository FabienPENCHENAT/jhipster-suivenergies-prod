import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD011InstalationChauffageComponent } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.component';
import { TD011InstalationChauffageService } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.service';
import { TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

describe('Component Tests', () => {
  describe('TD011InstalationChauffage Management Component', () => {
    let comp: TD011InstalationChauffageComponent;
    let fixture: ComponentFixture<TD011InstalationChauffageComponent>;
    let service: TD011InstalationChauffageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD011InstalationChauffageComponent],
      })
        .overrideTemplate(TD011InstalationChauffageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD011InstalationChauffageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD011InstalationChauffageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD011InstalationChauffage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD011InstalationChauffages && comp.tD011InstalationChauffages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
