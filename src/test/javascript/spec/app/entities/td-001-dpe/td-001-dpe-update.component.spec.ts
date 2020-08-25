import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD001DPEUpdateComponent } from 'app/entities/td-001-dpe/td-001-dpe-update.component';
import { TD001DPEService } from 'app/entities/td-001-dpe/td-001-dpe.service';
import { TD001DPE } from 'app/shared/model/td-001-dpe.model';

describe('Component Tests', () => {
  describe('TD001DPE Management Update Component', () => {
    let comp: TD001DPEUpdateComponent;
    let fixture: ComponentFixture<TD001DPEUpdateComponent>;
    let service: TD001DPEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD001DPEUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD001DPEUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD001DPEUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD001DPEService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD001DPE(123);
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
        const entity = new TD001DPE();
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
