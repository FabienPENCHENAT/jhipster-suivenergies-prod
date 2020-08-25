import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD002ConsommationsUpdateComponent } from 'app/entities/td-002-consommations/td-002-consommations-update.component';
import { TD002ConsommationsService } from 'app/entities/td-002-consommations/td-002-consommations.service';
import { TD002Consommations } from 'app/shared/model/td-002-consommations.model';

describe('Component Tests', () => {
  describe('TD002Consommations Management Update Component', () => {
    let comp: TD002ConsommationsUpdateComponent;
    let fixture: ComponentFixture<TD002ConsommationsUpdateComponent>;
    let service: TD002ConsommationsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD002ConsommationsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD002ConsommationsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD002ConsommationsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD002ConsommationsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD002Consommations(123);
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
        const entity = new TD002Consommations();
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
