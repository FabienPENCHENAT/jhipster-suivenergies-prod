import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD012GenerateurChauffageUpdateComponent } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage-update.component';
import { TD012GenerateurChauffageService } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.service';
import { TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

describe('Component Tests', () => {
  describe('TD012GenerateurChauffage Management Update Component', () => {
    let comp: TD012GenerateurChauffageUpdateComponent;
    let fixture: ComponentFixture<TD012GenerateurChauffageUpdateComponent>;
    let service: TD012GenerateurChauffageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD012GenerateurChauffageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD012GenerateurChauffageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD012GenerateurChauffageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD012GenerateurChauffageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD012GenerateurChauffage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD012GenerateurChauffage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
