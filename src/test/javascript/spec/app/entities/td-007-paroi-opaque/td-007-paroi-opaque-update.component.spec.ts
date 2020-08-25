import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD007ParoiOpaqueUpdateComponent } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque-update.component';
import { TD007ParoiOpaqueService } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.service';
import { TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

describe('Component Tests', () => {
  describe('TD007ParoiOpaque Management Update Component', () => {
    let comp: TD007ParoiOpaqueUpdateComponent;
    let fixture: ComponentFixture<TD007ParoiOpaqueUpdateComponent>;
    let service: TD007ParoiOpaqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD007ParoiOpaqueUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD007ParoiOpaqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD007ParoiOpaqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD007ParoiOpaqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD007ParoiOpaque(123);
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
        const entity = new TD007ParoiOpaque();
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
