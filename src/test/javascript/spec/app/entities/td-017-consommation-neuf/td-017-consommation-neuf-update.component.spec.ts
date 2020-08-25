import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD017ConsommationNeufUpdateComponent } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf-update.component';
import { TD017ConsommationNeufService } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.service';
import { TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

describe('Component Tests', () => {
  describe('TD017ConsommationNeuf Management Update Component', () => {
    let comp: TD017ConsommationNeufUpdateComponent;
    let fixture: ComponentFixture<TD017ConsommationNeufUpdateComponent>;
    let service: TD017ConsommationNeufService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD017ConsommationNeufUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD017ConsommationNeufUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD017ConsommationNeufUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD017ConsommationNeufService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD017ConsommationNeuf(123);
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
        const entity = new TD017ConsommationNeuf();
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
