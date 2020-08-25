import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD008BaieUpdateComponent } from 'app/entities/td-008-baie/td-008-baie-update.component';
import { TD008BaieService } from 'app/entities/td-008-baie/td-008-baie.service';
import { TD008Baie } from 'app/shared/model/td-008-baie.model';

describe('Component Tests', () => {
  describe('TD008Baie Management Update Component', () => {
    let comp: TD008BaieUpdateComponent;
    let fixture: ComponentFixture<TD008BaieUpdateComponent>;
    let service: TD008BaieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD008BaieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD008BaieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD008BaieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD008BaieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD008Baie(123);
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
        const entity = new TD008Baie();
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
