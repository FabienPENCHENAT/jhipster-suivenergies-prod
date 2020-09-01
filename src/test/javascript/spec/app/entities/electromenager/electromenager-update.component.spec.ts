import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ElectromenagerUpdateComponent } from 'app/entities/electromenager/electromenager-update.component';
import { ElectromenagerService } from 'app/entities/electromenager/electromenager.service';
import { Electromenager } from 'app/shared/model/electromenager.model';

describe('Component Tests', () => {
  describe('Electromenager Management Update Component', () => {
    let comp: ElectromenagerUpdateComponent;
    let fixture: ComponentFixture<ElectromenagerUpdateComponent>;
    let service: ElectromenagerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ElectromenagerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ElectromenagerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElectromenagerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElectromenagerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Electromenager(123);
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
        const entity = new Electromenager();
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
