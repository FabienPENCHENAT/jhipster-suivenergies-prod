import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD010PontsThermiquesUpdateComponent } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques-update.component';
import { TD010PontsThermiquesService } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.service';
import { TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

describe('Component Tests', () => {
  describe('TD010PontsThermiques Management Update Component', () => {
    let comp: TD010PontsThermiquesUpdateComponent;
    let fixture: ComponentFixture<TD010PontsThermiquesUpdateComponent>;
    let service: TD010PontsThermiquesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD010PontsThermiquesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TD010PontsThermiquesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD010PontsThermiquesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD010PontsThermiquesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TD010PontsThermiques(123);
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
        const entity = new TD010PontsThermiques();
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
