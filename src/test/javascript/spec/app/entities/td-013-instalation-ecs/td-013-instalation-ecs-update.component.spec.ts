import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD013InstalationECSUpdateComponent } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs-update.component';
import { TD013InstalationECSService } from 'app/entities/td-013-instalation-ecs/td-013-instalation-ecs.service';
import { TD013InstalationECS } from 'app/shared/model/td-013-instalation-ecs.model';

describe('Component Tests', () => {
  describe('TD013InstalationECS Management Update Component', () => {
    let comp: TD013InstalationECSUpdateComponent;
    let fixture: ComponentFixture<TD013InstalationECSUpdateComponent>;
    let service: TD013InstalationECSService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD013InstalationECSUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD013InstalationECSUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD013InstalationECSUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD013InstalationECSService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD013InstalationECS(123);
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
        const entity = new TD013InstalationECS();
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
