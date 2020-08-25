import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD006BatimentUpdateComponent } from 'app/entities/td-006-batiment/td-006-batiment-update.component';
import { TD006BatimentService } from 'app/entities/td-006-batiment/td-006-batiment.service';
import { TD006Batiment } from 'app/shared/model/td-006-batiment.model';

describe('Component Tests', () => {
  describe('TD006Batiment Management Update Component', () => {
    let comp: TD006BatimentUpdateComponent;
    let fixture: ComponentFixture<TD006BatimentUpdateComponent>;
    let service: TD006BatimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD006BatimentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD006BatimentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD006BatimentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD006BatimentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD006Batiment(123);
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
        const entity = new TD006Batiment();
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
