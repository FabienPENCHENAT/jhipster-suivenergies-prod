import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD015ProductionEnergiesUpdateComponent } from 'app/entities/td-015-production-energies/td-015-production-energies-update.component';
import { TD015ProductionEnergiesService } from 'app/entities/td-015-production-energies/td-015-production-energies.service';
import { TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

describe('Component Tests', () => {
  describe('TD015ProductionEnergies Management Update Component', () => {
    let comp: TD015ProductionEnergiesUpdateComponent;
    let fixture: ComponentFixture<TD015ProductionEnergiesUpdateComponent>;
    let service: TD015ProductionEnergiesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD015ProductionEnergiesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD015ProductionEnergiesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD015ProductionEnergiesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD015ProductionEnergiesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD015ProductionEnergies(123);
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
        const entity = new TD015ProductionEnergies();
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
