import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD014GenerateurECSUpdateComponent } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs-update.component';
import { TD014GenerateurECSService } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.service';
import { TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

describe('Component Tests', () => {
  describe('TD014GenerateurECS Management Update Component', () => {
    let comp: TD014GenerateurECSUpdateComponent;
    let fixture: ComponentFixture<TD014GenerateurECSUpdateComponent>;
    let service: TD014GenerateurECSService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD014GenerateurECSUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD014GenerateurECSUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD014GenerateurECSUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD014GenerateurECSService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD014GenerateurECS(123);
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
        const entity = new TD014GenerateurECS();
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
