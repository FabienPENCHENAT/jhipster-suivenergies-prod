import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD011InstalationChauffageUpdateComponent } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage-update.component';
import { TD011InstalationChauffageService } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.service';
import { TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

describe('Component Tests', () => {
  describe('TD011InstalationChauffage Management Update Component', () => {
    let comp: TD011InstalationChauffageUpdateComponent;
    let fixture: ComponentFixture<TD011InstalationChauffageUpdateComponent>;
    let service: TD011InstalationChauffageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD011InstalationChauffageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD011InstalationChauffageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD011InstalationChauffageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD011InstalationChauffageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD011InstalationChauffage(123);
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
        const entity = new TD011InstalationChauffage();
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
